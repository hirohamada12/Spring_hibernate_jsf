package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.PhoneNumber;
import com.service.EmployeeService;

@Component(value = "uploadFileData")
public class FileUpload {

	@Autowired
	private EmployeeService employeeService;

	private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void upload() {
		if (uploadedFile != null) {
			ArrayList<PhoneNumber> list = new ArrayList<PhoneNumber>();
			String str = "";
			int count = 0;
			// StringBuffer buf = new StringBuffer();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream()));
				while ((str = reader.readLine()) != null) {
					String arr[] = str.split(",");
					list.add(new PhoneNumber(arr[0], arr[1]));
				}
			} catch (IOException E) {
				E.printStackTrace();
			}
			System.out.println(count + "Size" + list.size());
			employeeService.insertPhoneNumber(list);
			FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			FacesMessage message = new FacesMessage("Failed", uploadedFile.getFileName() + " not uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
