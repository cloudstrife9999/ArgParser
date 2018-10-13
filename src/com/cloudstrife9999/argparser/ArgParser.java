package com.cloudstrife9999.argparser;

import java.util.HashMap;
import java.util.Map;

public class ArgParser {
    private String[] requiredArguments;
    
    public ArgParser(String... required) {
	this.requiredArguments = required;
    }
    
    public Map<String, String> parse(String... args) {
	Map<String, String> parameters = new HashMap<>();
	
	for(String required : this.requiredArguments) {
	    if(!lookForArgument(required, args, parameters)) {
		throw new MissingArgumentException("Argument '" + required + "' is required.");
	    }
	}
	
	return parameters;
    }

    private boolean lookForArgument(String required, String[] args, Map<String, String> parameters) {
	for(int i = 0; i < args.length - 1; i++) {
	    if(required.equals(args[i])) {
		return lookForValue(required, args[i + 1], parameters);
	    }
	}
	
	return false;
    }

    private boolean lookForValue(String required, String arg, Map<String, String> parameters) {
	for(String req : this.requiredArguments) {
	    if(req.equals(arg)) {
		return false;
	    }
	}
	
	parameters.put(required, arg);
	
	return true;
    }
}