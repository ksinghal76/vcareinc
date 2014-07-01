package com.vcareinc.controllers;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Controller;

@Controller
public class FileUploadController extends BaseMultiActionController {

	private Logger log = Logger.getLogger(FileUploadController.class);

	public void handleFileUpload(FileUploadEvent event) {
		log.info("testing...");
		log.info(event.getFile().getFileName());
	}
}
