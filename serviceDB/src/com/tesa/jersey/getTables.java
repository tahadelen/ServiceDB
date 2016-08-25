package com.tesa.jersey;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.tesa.model.Tables;


@Path("/getTables")
public class getTables {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	@GET
	@Path("/tables")
	@Produces("application/json")
	public List<Tables> getTable() throws SQLException, Exception {
			return DBConnection.getTables();
	}
	
}
