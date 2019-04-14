package fi.tuni.tastingapp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fi.tuni.tastingapp.repository.StorageService;

@RestController
public class ImageController {
	
	/*
	 * TODO: Implement this stuff in a better way. Currently super messy.
	 */
	
    private static final Path IMG_FOLDER_PATH = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/");
	
	@RequestMapping(value = "images/upload", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file,
			@RequestParam(required = true) long beerId) throws IllegalStateException, IOException {
		
		/* Converting to .png if not already */
		if(!file.getContentType().equalsIgnoreCase("image/png")) {
			
		    File convFile = new File(IMG_FOLDER_PATH + "/drink_" + beerId + ".png");
		    file.transferTo(convFile);
		    
		    BufferedImage bufferedImage = ImageIO.read(convFile);
			ImageIO.write(bufferedImage, "png", convFile);
		} else {
			file.transferTo(new File(IMG_FOLDER_PATH + "/drink_" + beerId + ".png"));
		}
		return "";
	}
	
	@RequestMapping(value = "images/get/{beerId}", method = RequestMethod.GET)
	public ResponseEntity<Resource> serveFile(@PathVariable("beerId") long beerId) {
        Resource file = loadAsResource(getBeerImageFilename(beerId));
        return ResponseEntity
                .ok()
                .body(file);
	}
	
    public Resource loadAsResource(String filename) {
        try {
            Path file = IMG_FOLDER_PATH.resolve(filename);
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
    
    private String getBeerImageFilename(long beerId) {
    	return String.format("drink_%d.png", beerId);
    }

}
