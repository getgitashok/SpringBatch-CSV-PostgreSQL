package com.javasampleapproach.batchcsvpostgresql.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@RestController
@Api(value="Controller Service", description="Controller Service")
public class WebController {
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"\\src\\main\\resources\\";
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;
	

	@RequestMapping( value= {"/runjob"}, method = RequestMethod.GET)
	public String handle() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "Done! Check Console Window for more details";
	}
	
	@RequestMapping(value = {"/uploadAndSave"},  method = RequestMethod.POST)
	public String uploadAndSave(@RequestParam("file") MultipartFile file) throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			// Get the file and save it somewhere
			System.out.println("uploading file");
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            System.out.println("path to upload file " +path);
            Files.write(path, bytes);
            
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "data inserted to postgres successfully";
	}
}
