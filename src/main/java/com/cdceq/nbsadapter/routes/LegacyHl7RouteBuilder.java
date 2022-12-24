package com.cdceq.nbsadapter.routes;

import org.apache.camel.Exchange;
import  org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.beans.factory.annotation.Value;
import  org.springframework.stereotype.Component;

import  org.apache.camel.builder.RouteBuilder;

import	lombok.NoArgsConstructor;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

import  com.cdceq.nbsadapter.exceptions.ValidationException;
import	com.cdceq.nbsadapter.processors.Hl7ToXmlTransformer;
import	com.cdceq.nbsadapter.processors.XmlDataPersister;
@Component
@NoArgsConstructor
public class LegacyHl7RouteBuilder extends RouteBuilder {
    private static Logger logger = LoggerFactory.getLogger(LegacyHl7RouteBuilder.class);

	@Value("${report-stream.hl7-files-url}")
	private String hl7FilesDirectoryUrl;

	@Autowired
	private Hl7ToXmlTransformer hl7ToXmlTransformer;

	@Autowired
	private XmlDataPersister xmlDataPersister;

    @Override
    public void configure() throws Exception {
		logger.info("Report stream hl7 files directory = {}", hl7FilesDirectoryUrl);

        onException(ValidationException.class)
        .log("Observed validation exception")
        .markRollbackOnly()
        .useOriginalMessage()
        .logStackTrace(true)        
        .end();

        onException(Exception.class)
        .log("Observed exception")
        .markRollbackOnly()
        .useOriginalMessage()
        .logStackTrace(true)
        .end();
        
		from(hl7FilesDirectoryUrl)
		.routeId("Legacy.Hl7.FilesConsumer.Route")
		.process(hl7ToXmlTransformer)
		.log("Xml: ${body}")
		.process(xmlDataPersister)
		.log("Persisted file ${headers.CamelFileName} as xml message to sql server database")
		.end();
    }
}