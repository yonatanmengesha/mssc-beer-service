package jotech.springframwork.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jotech.springframwork.msscbeerservice.service.BeerService;
import jotech.springframwork.msscbeerservice.web.mappers.BeerMapper;
import jotech.springframwork.msscbeerservice.web.model.BeerDto;
import jotech.springframwork.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.jotech", uriPort = 80)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "jotech.springframwork.msscbeerservice.web.mappers")
 class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;


    BeerDto validBeer;

    @BeforeEach
     void setUp() {

        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Beer 1")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .price(new BigDecimal(12.00))
                .upc(2465879314L)
                .build();
    }

    @Test
     void getBeerById() throws Exception {

       given(beerService.findBeerById(any(UUID.class))).
                willReturn(validBeer);

      //  given(beerRepository.findById(any(UUID.class))).
       //         willReturn(Optional.of(beerMapper.beerDtoToBeer(validBeer)));

        mockMvc.perform(get("/api/v1/beer/{beerId}", validBeer.getId().toString())
                        .param("iscold", "yes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                 .andExpect(jsonPath("$.beerName",is(validBeer.getBeerName())))
                .andDo(document("v1/beer-get",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("Is Beer Cold Query param")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version number"),
                                fieldWithPath("createdDate").description("Date Created"),
                                fieldWithPath("lastModifiedDate").description("Date Updated"),
                                fieldWithPath("beerName").description("Beer Name"),
                                fieldWithPath("beerStyle").description("Beer Style"),
                                fieldWithPath("upc").description("UPC of Beer"),
                                fieldWithPath("price").description("Price"),
                                fieldWithPath("quantityOnHand").description("Quantity On hand")
                        )));
    }

    @Test

     void saveNewBeer() throws Exception {

      //  UUID beerIdFound= UUID.randomUUID();
        BeerDto beerDto = getValidBeerDto();

      //  beerService.saveBeer(beerDto);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveBeer(any())).willReturn(beerDto);

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of the beer"),
                                fields.withPath("beerStyle").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()
                        )));
    }

    @Test
     void updateBeerById() throws Exception {

        UUID beerId =UUID.randomUUID();
        BeerDto updatedBeerDto = BeerDto
                .builder()
                .id(beerId)
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("4.00"))
                .upc(123456789L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(updatedBeerDto);

        given(beerService.updateBeer(any(),any())).willReturn(updatedBeerDto);
        when(beerService.updateBeer(eq(beerId),any(BeerDto.class))).thenReturn(updatedBeerDto);
        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(put("/api/v1/beer/{beerId}", beerId.toString()).
                        contentType(MediaType.APPLICATION_JSON).
                        content(beerDtoJson))
                .andExpect(status().isNoContent())
                .andDo(document("v1/beer-update"
                        , requestFields(

                                fields.withPath("id").description("The Id of the Beer"),
                                fields.withPath("version").description("The version of the Beer app"),
                                fields.withPath("createdDate").description("The day it is created"),
                                fields.withPath("lastModifiedDate").description("The last dqy it is modified"),
                                fields.withPath("beerName").description("Name of the Beer"),
                                fields.withPath("beerStyle").description("Style of the Beer"),
                                fields.withPath("upc").description("Beer Upc").attributes(),
                                fields.withPath("price").description("The price of the Beer"),
                                fields.withPath("quantityOnHand").ignored()

                        )));
    }

    BeerDto getValidBeerDto() {
        return BeerDto
                .builder()
                .id(UUID.randomUUID())
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("4.00"))
                .upc(123456789L)
                .build();
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}