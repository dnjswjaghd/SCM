package jjj.scm.test;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "테스트 디티오")
public class TestDTO {
    @Schema(description = "이거는 유저아이디임")
    private String userId;
}
