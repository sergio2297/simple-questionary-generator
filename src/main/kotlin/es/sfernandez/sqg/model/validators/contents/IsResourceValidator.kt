package es.sfernandez.sqg.model.validators.contents

import es.sfernandez.sqg.beans.contents.IsResource
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class IsResourceValidator : Validator<IsResource> {

    override fun validate(value: IsResource): ValidationResult<IsResource> {
        return checkIfPathIsEmpty(value)
    }

    private fun checkIfPathIsEmpty(resource: IsResource): ValidationResult<IsResource> {
        return if(resource.path.trim().isEmpty())
                ValidationResult.error("Error. The path of a resource can't be empty", resource)
            else
                ValidationResult.ok()
    }

}