package jjj.scm.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TestSwaggerController", description = "테스트 컨트롤러")
public interface TestSwaggerController {
    @Operation(
            summary = "테스트 요청 경로",
            description = "테스트 요청 경로"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "성공", content = {
                            @Content(schema = @Schema(implementation = String.class))
                    })
            }
    )
    String test1();
    String testDto(@Parameter(description = "테스트 디티오입니다", required = true) @RequestBody(
            content = @Content(examples = {
                    @ExampleObject(name = "테스트 디티오 예제", value = """
                            {
                                "userId" : "testId"
                            }
                            """)
            })
    ) TestDTO dto);
}
