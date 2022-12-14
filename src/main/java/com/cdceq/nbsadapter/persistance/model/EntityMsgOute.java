package com.cdceq.nbsadapter.persistance.model;

import  javax.persistence.Column;
import  javax.persistence.Entity;
import  javax.persistence.Id;
import  javax.persistence.Table;

import	lombok.NoArgsConstructor;
import	lombok.Getter;
import	lombok.Setter;

@Entity
@Table(name = "NBS_MSGOUTE")
@NoArgsConstructor
@Getter
@Setter
public class EntityMsgOute {
	private static String TOSTRING_COLUMN_SPACER = ", ";
	
    @Id
    private String id;

    @Column(name = "NETSS_TransportQ_out", length = 2048, nullable = false)
    private String netssTransportQOut;

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.getNetssTransportQOut())
    	  .append(TOSTRING_COLUMN_SPACER)
    	  ;
    	
        return sb.toString();
    }
}