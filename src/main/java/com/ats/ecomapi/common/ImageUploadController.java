package com.ats.ecomapi.common;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.ecomapi.mst_model.Info;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin
public class ImageUploadController {
	
	
	@RequestMapping(value = { "/sendEmail" }, method = RequestMethod.POST)
	public @ResponseBody Info sendEmail(@RequestParam("recipientEmail") String recipientEmail,
			@RequestParam("mailsubject") String mailsubject,
	 		@RequestParam("mailMsg") String mailMsg) {
				
		Info info = new Info();
		try {
		EmailUtility.sendEmailer(recipientEmail, mailsubject, mailMsg);
	
		info.setError(false);
		}catch (Exception e) {
			info.setError(true);
			return info;
		}
		
		return info;
	}
	
	
	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("imageName") String imageName) {

		Info info = new Info();
		System.out.println("File Name " + uploadfile.getOriginalFilename());
		String a=	imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm 2021-03-11
		System.out.println("imageName Name1 " +a);
		
		
		
		try {
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
			saveUploadedFiles(Arrays.asList(uploadfile), a);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {
			System.err.println("Exceptn In getFarmerContract ");
			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	@RequestMapping(value = { "/photoUpload1" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract1(@RequestParam("file") List<MultipartFile> uploadfile,
			@RequestParam("imageName") List<String> imageName) {

		Info info = new Info();
		/*
		 * System.out.println("File Name " + uploadfile.getOriginalFilename()); String
		 * a= imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm
		 * 2021-03-11 System.out.println("imageName Name1 " +a);
		 */
		
		
		try {
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
			//saveUploadedFiles(Arrays.asList(uploadfile), a);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);
			for(int i=0;i<uploadfile.size();i++) {
				String imageName2=imageName.get(i).replace('"', ' ');
				saveUploadedFiles(Arrays.asList(uploadfile.get(i)),imageName2);
				info.setError(false);
				info.setMessage("File uploaded successfully");

			}

			/*
			 * info.setError(false); info.setMessage("File uploaded successfully");
			 */

		} catch (IOException e) {
			System.err.println("Exceptn In getFarmerContract ");
			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	
	// save file
	private void saveUploadedFiles(List<MultipartFile> files, String imageName) throws IOException {
		
		try {
			for (MultipartFile file : files) {
				Path path = null;
				if (file.isEmpty()) {
					continue;
				}
			
				path = Paths.get(ApiConstants.UPLOAD_URL+imageName.trim());
				 System.out.println(path.toAbsolutePath());
			
				byte[] bytes = file.getBytes();

				Files.write(path, bytes);

			

		}
			
		}catch (Exception e) {
			System.err.println("Exceptn In saveUploadedFiles ");
			e.printStackTrace();
		}

	}
	

}
