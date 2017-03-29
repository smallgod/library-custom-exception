package com.library.customexception;

import com.library.datamodel.Constants.ErrorCategory;
import com.library.datamodel.Constants.ErrorCode;
import com.library.datamodel.Json.ErrorResponse;
import com.library.datamodel.model.v1_0.Errorresponse;
import com.library.utilities.LoggerUtil;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author smallgod
 */
public class MyCustomException extends Throwable {

    private static final long serialVersionUID = 895611192872487357L;

    private static final LoggerUtil logger = new LoggerUtil(MyCustomException.class);

    private ErrorCode errorCode;
    private String errorDetails;
    private ErrorCategory errorCategory;
    private String requestId;

    //pass an error object fully created instead
    public MyCustomException() {
        super();
    }

    public MyCustomException(String message, ErrorCode errorCode, String errorDetails, ErrorCategory errorCategory, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.errorCategory = errorCategory;
    }

    public MyCustomException(String message, ErrorCode errorCode, String errorDetails, String requestId) {

        super(message);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.requestId = requestId;

        logger.error("MyCustomError | " + this.errorDetails);
    }

    public MyCustomException(String message, ErrorCode errorCode, String errorDetails, String requestId, Throwable cause) {

        super(message, cause);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.requestId = requestId;

        logger.error("MyCustomError | " + this.errorDetails);
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

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public ErrorCategory getErrorCategory() {
        return errorCategory;
    }

    /**
     * Create error response
     *
     * @return
     */
    public ErrorResponse createErrorResponse() {
        //create an error object to send to the error-page

        ErrorResponse response = new ErrorResponse();
        ErrorResponse.Data data = response.new Data();

        ErrorResponse.Data.Error error = data.new Error();
        error.setErrorCode(this.errorCode.getValue());
        error.setDescription(this.errorDetails);

        Set<ErrorResponse.Data.Error> errors = new HashSet<>();
        errors.add(error);

        data.setRequestId(this.requestId);
        data.setErrors(errors);

        response.setData(data);

        return response;
    }

}
