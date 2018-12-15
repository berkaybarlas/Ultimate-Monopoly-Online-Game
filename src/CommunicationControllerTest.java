import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import org.junit.Test;

public class CommunicationControllerTest {


    CommunicationController cc;
    GameEngine gg;
    PlayerController pp;
    MoneyController mc;

    @org.junit.Before
    public void setUp() throws Exception {
        cc = CommunicationController.getInstance();
        gg = GameEngine.getInstance();
        pp = PlayerController.getInstance();
        mc = MoneyController.getInstance();
    }

    @Test
    public void startServer() {

    }

    @Test
    public void processInput() {

    }
}