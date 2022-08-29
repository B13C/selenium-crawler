package cn.gaple.crawler.protocol.res;

import cn.maple.core.framework.dto.protocol.res.GXBaseResProtocol;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CrawlerResProtocol extends GXBaseResProtocol {
    private String content;
}
