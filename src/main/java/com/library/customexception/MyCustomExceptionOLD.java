package com.library.customexception;

import com.library.datamodel.Constants.ErrorCategory;
import com.library.datamodel.Constants.ErrorCode;
import com.library.datamodel.model.v1_0.Errorresponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author smallgod
 */
public class MyCustomExceptionOLD extends Throwable {
    
    private static final long serialVersionUID = 2127284714477086864L;
     private static final Logger logger = LoggerFactory.getLogger(MyCustomExceptionOLD.class);
    
    private ErrorCode errorCode;
    private String errorDetails;
    private ErrorCategory errorCategory;

    //pass an error object fully created instead
    public MyCustomExceptionOLD() {
        super();
    }

    /*public MyCustomExceptionOLD(String message, Errorresponse errorResponse) {
     super(message);
     this.errorResponse = errorResponse;
     }*/
    /***
     * 
     * @param message
     * @param errorCode
     * @param errorDetails
     * @param errorCategory 
     */
    public MyCustomExceptionOLD(String message, ErrorCode errorCode, String errorDetails, ErrorCategory errorCategory) {
        
        super(message);
        
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.errorCategory = errorCategory;
        
        logger.error("MyCustomError | " + getErrorDetails());
    }

    public MyCustomExceptionOLD(String message, ErrorCode errorCode, String errorDetails, ErrorCategory errorCategory, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.errorCategory = errorCategory;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " with errorCode :" + errorCode.getValue();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public ErrorCategory getErrorCategory() {
        return errorCategory;
    }

    /**
     * 
     * @return 
     */
    public Errorresponse createErrorResponse() {
        //create an error object to send to the error-page
        Errorresponse.Error error = new Errorresponse.Error();
        error.setCode(this.errorCode.getValue());
        error.setDetails(this.errorDetails);
        error.setAdditional(null);

        Errorresponse errorResponse = new Errorresponse();
        errorResponse.setCategory(this.errorCategory.getValue());
        errorResponse.setTypename("errorresponse");
        errorResponse.setVersion("1.0");
        errorResponse.setError(error);

        return errorResponse;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
