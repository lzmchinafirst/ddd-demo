package com.purang.manifest.adapter.converter;

import com.purang.manifest.adapter.dto.DeliverManifestFormCmd;
import com.purang.manifest.adapter.dto.DeliverManifestUpdateCmd;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * DO构造器（DTO -> DO）
 */
@Mapper
public interface DeliverManifestDoConverter {
    DeliverManifestDoConverter INSTANCE = getMapper(DeliverManifestDoConverter.class);

    DeliverManifestDO toDeliverManifestDO(DeliverManifestFormCmd source);

    //    @Mapping(source = "businessNo", target = "businessNo.value")
    DeliverManifestDO toDeliverManifestDO(DeliverManifestUpdateCmd source);
}
