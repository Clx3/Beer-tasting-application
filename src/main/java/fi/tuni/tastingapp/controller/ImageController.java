package fi.tuni.tastingapp.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
	
	private static final Path IMG_FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/src/main/resources/images/");
	
	@RequestMapping("images/upload")
	public String uploadImage(@RequestParam MultipartFile file, @RequestParam long beerId) throws IllegalStateException, IOException {
		if(!file.getContentType().equalsIgnoreCase("image/png")) {
			File toBeConvertedFile = new File(getBeerImgPath(beerId).toString());
			file.transferTo(toBeConvertedFile);
			BufferedImage bufferedImage = ImageIO.read(toBeConvertedFile);
			ImageIO.write(bufferedImage, "png", toBeConvertedFile);
			
		} else {
			file.transferTo(new File(getBeerImgPath(beerId).toUri()));
		}
		return "";
	}
	
	@RequestMapping(value = "images/get/{beerId}")
	public ResponseEntity<Resource> getImage(@PathVariable long beerId) {
        Resource file = loadAsResource(beerId);
        return ResponseEntity
                .ok()
                .body(file);
    }

     public Resource loadAsResource(long beerId) {
        try {
            Path file = getBeerImgPath(beerId);
            System.out.println(file.toString());
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                System.out.println("no file");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        return null;
    }
     
    private Path getBeerImgPath(long beerId) {
    	return Paths.get(System.getProperty("user.dir") + "/src/main/resources/images/", getBeerImgName(beerId));
    }
	
	private String getBeerImgName(long beerId) {
		return String.format("drink_%d.png", beerId);
	}

}
