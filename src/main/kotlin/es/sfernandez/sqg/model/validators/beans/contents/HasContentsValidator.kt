package es.sfernandez.sqg.model.validators.beans.contents

import es.sfernandez.sqg.beans.contents.HasContents
import es.sfernandez.sqg.model.validators.ValidationResult
import es.sfernandez.sqg.model.validators.Validator

class HasContentsValidator : Validator<HasContents> {

    //---- Methods ----
    override fun validate(value: HasContents): ValidationResult<HasContents> {
        return checkIfHasNotGotContents(value)
    }

    private fun checkIfHasNotGotContents(hasContents: HasContents): ValidationResult<HasContents> {
        return if(hasContents.groupOfContents.size() > 0)
                ValidationResult.ok()
            else
                ValidationResult.warning("Warning. HasContents without contents detected", hasContents)
    }

}