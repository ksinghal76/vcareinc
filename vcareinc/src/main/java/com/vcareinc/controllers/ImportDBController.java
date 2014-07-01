package com.vcareinc.controllers;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Configuration
@EnableBatchProcessing
public class ImportDBController extends BaseMultiActionController {

	protected final Logger log = Logger.getLogger(ImportDBController.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job runBatchDB;

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public Job getRunBatchDB() {
		return runBatchDB;
	}

	public void setRunBatchDB(Job runBatchDB) {
		this.runBatchDB = runBatchDB;
	}

	@RequestMapping("/importDB")
	@ResponseBody
	public void runImportDB() {
		try {
			JobParametersBuilder builder = new JobParametersBuilder();
			JobExecution execution = jobLauncher.run(runBatchDB, builder.toJobParameters());
			log.info(execution.getStatus());
		} catch (Exception e) {
			log.error(e);
		}
	}
}
