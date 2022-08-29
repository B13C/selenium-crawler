package cn.gaple.crawler.protocol.req;

import cn.maple.core.framework.dto.protocol.res.GXBaseResProtocol;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CrawlerReqProtocol extends GXBaseResProtocol {
    private String content;
}
