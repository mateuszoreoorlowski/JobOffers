package spring.joboffers.error;

import spring.joboffers.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("integration")
public class OfferUrlDuplicateErrorIntegrationTest extends BaseIntegrationTest {

    static {
        MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.12"));
        mongoDBContainer.start();
        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }

    @Test
    @WithMockUser
    public void should_return_409_conflict_when_added_second_offer_with_same_offer_url() throws Exception {
        // step 1
        // given && when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "companyName": "someCompany",
                        "position": "somePosition",
                        "salary": "7 000 - 9 000 PLN",
                        "offerUrl": "https://newoffers.pl/offer/1234"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
        );
        // then
        perform.andExpect(status().isCreated());


        // step 2
        // given && when
        ResultActions perform1 = mockMvc.perform(post("/offers")
                .content("""
                        {
                        "companyName": "someCompany",
                        "position": "somePosition",
                        "salary": "7 000 - 9 000 PLN",
                        "offerUrl": "https://newoffers.pl/offer/1234"
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
        );
        // then
        perform1.andExpect(status().isConflict());
    }
}
