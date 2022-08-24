package controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softtech.EmsmApplication;
import com.softtech.controller.ExpensesManagementController;
import com.softtech.service.ExpensesManagementService;


@ContextConfiguration(classes = EmsmApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ExpensesManagementController.class)
public class ExpensesManagementControllerTest {
	@Autowired
    private MockMvc mockMvc;

	// TransportControllerに全部サービスをＭｏｃｋする。
    @MockBean
    private ExpensesManagementService service;

    @InjectMocks // モックオブジェクトの注入
    private ExpensesManagementService itemController;

    @BeforeEach // テストメソッド（@Testをつけたメソッド）実行前に都度実施
    public void initmocks() {
        MockitoAnnotations.initMocks(this); // アノテーションの有効化
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build(); // MockMvcのセットアップ
    }

    @Test
    public void test1() throws Exception {

//    	Transport transport = new Transport();
//        Mockito.when(service.queryTransport(new HashMap<String, String>())).thenReturn(transport);

        //mockMvc.perform(MockMvcRequestBuilders.get("/initExpensesManagement").accept(MediaType.APPLICATION_JSON));
    	//mockMvc.perform(MockMvcRequestBuilders.get("/abc123").accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/initExpensesManagement").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.view().name("expensesManagement"))
        .andReturn();

    }

}
