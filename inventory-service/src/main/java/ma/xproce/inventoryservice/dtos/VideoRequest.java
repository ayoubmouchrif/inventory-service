package ma.xproce.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private Date datePublication;
    //?
    private Creator creator;
}
