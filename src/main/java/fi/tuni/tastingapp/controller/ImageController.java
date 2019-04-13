package fi.tuni.tastingapp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fi.tuni.tastingapp.repository.StorageService;

@RestController
public class ImageController {
	
    private static final String IMG_FOLDER_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
	
	@RequestMapping(value = "imageUpload", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		/* Converting to .png if not already */
		System.out.println(file.getContentType());
		if(!file.getContentType().equalsIgnoreCase("image/png")) {
			
		    File convFile = new File(IMG_FOLDER_PATH + "drink_1.png");
		    file.transferTo(convFile);
		    
		    BufferedImage bufferedImage = ImageIO.read(convFile);
			ImageIO.write(bufferedImage, "png", convFile);
		} else {
			file.transferTo(new File(IMG_FOLDER_PATH + "drink_2.png"));
		}
		return "";
	}

}
