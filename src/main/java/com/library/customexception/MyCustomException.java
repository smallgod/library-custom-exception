package com.library.customexception;

import com.library.datamodel.Json.ErrorResponse;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author smallgod
 */
public class MyCustomException extends Throwable {

    private static final long serialVersionUID = 895611192872487357L;

    private String requestId;
    private Set<ErrorWrapper> errors;

    public MyCustomException(String requestId, Set<ErrorWrapper> errors) {

        super();
        this.requestId = requestId;
        this.errors = errors;

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
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

        Set<ErrorResponse.Data.Error> responseErrors = new HashSet<>();

        for (ErrorWrapper errorWrapper : this.errors) {

            ErrorResponse.Data.Error error = data.new Error();
            error.setErrorCode(errorWrapper.getErrorCode().getValue());
            error.setDescription(errorWrapper.getErrorDetails());
            error.setAdditionalDetails(errorWrapper.getDescription());

            responseErrors.add(error);
        }

        data.setRequestId(this.requestId);
        data.setErrors(responseErrors);

        response.setData(data);

        return response;
    }

    public Set<ErrorWrapper> getErrors() {
        return errors;
    }

    public void setErrors(Set<ErrorWrapper> errors) {
        this.errors = errors;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
