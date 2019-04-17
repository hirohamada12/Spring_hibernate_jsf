package com.controller;

import java.io.InputStream;

import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Service;

@Service
public class FileDownload {

	private StreamedContent file;

	public FileDownload() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/resources/demo/images/boromir.jpg");
		file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_boromir.jpg");
	}

	public StreamedContent getFile() {
		return file;
	}
}
