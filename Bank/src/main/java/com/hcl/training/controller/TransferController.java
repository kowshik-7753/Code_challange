package com.hcl.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.model.Transfer;
import com.hcl.training.service.ServiceInf;

@RestController
@RequestMapping("/bank")
public class TransferController {
	@Autowired
	ServiceInf service;
	private static String result;

	@PostMapping("/transfer")
	public @ResponseBody String tranferAmount(@RequestBody Transfer transfer) {
		Thread thread=new Thread(new Runnable() {
        String result1;
			@Override
			public void run() {
				result1=service.transferAmount(transfer);;
				result=result1;
			}
			
			
		});
		thread.start();
		try {
			thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;

	}
}
