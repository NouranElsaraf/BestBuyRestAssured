package Base;

import Client.RestClient;
import org.testng.annotations.BeforeClass;

    public class BaseTest {
        protected RestClient restClient;

        @BeforeClass
        public void setup() {
            restClient = new RestClient();
        }
    }

