package com.br.repogit.presentation.mapper

import com.br.repogit.domain.model.OwnerDomain
import com.br.repogit.presentation.model.OwnerPresentation
import com.br.repogit.utils.Mapper

class OwnerPresentationMapper : Mapper<OwnerDomain?, OwnerPresentation> {

    override fun map(source: OwnerDomain?): OwnerPresentation {
        return OwnerPresentation(
            name = source?.name.orEmpty(),
            avatarURL = source?.avatarURL.orEmpty()
        )
    }
}