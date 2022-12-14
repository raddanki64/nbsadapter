package com.cdceq.nbsadapter.services;

import	com.cdceq.nbsadapter.persistance.model.EntityMsgOute;
import 	com.cdceq.nbsadapter.persistance.NbsMsgOuteRepository;

import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Service;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

import	lombok.NoArgsConstructor;

import	java.util.Date;

@Service
@NoArgsConstructor
public class ElrDataServiceProvider {
	private static Logger LOG = LoggerFactory.getLogger(ElrDataServiceProvider.class);
	
    @Autowired
    private NbsMsgOuteRepository msgOuteRepo;
    
    public boolean saveMessage(String msgXml) {
    	EntityMsgOute msg = new EntityMsgOute();
    	
    	Date dt = new Date();
    	String strId = "" + dt.getTime();
    	
    	msg.setId(strId);
    	msg.setNetssTransportQOut(msgXml);
    	
    	msgOuteRepo.save(msg);	
    	
    	return true;
    }
}