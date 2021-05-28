package com.br.repogit.data.mapper

import com.br.repogit.data.model.Owner
import com.br.repogit.domain.model.OwnerDomain
import com.br.repogit.utils.Mapper

class OwnerDomainMapper : Mapper<Owner, OwnerDomain> {

    override fun map(source: Owner): OwnerDomain {
        return OwnerDomain(
            name = source.name.orEmpty(),
            avatarURL = source.avatarURL.orEmpty()
        )
    }
}