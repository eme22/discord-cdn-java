import com.eme22.discordcdn.Discord;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class DiscordTest {

    private String testToken = "";
    private String oldUrl = "";

    @Test
    public void fetchLatestLink_WithRealData() {
        // Skip this test if testToken or oldUrl is null
        Assumptions.assumeTrue(testToken != null && oldUrl != null, "Test token or old URL is not available");

        if (testToken.isEmpty() || oldUrl.isEmpty()) {
            return;
        }

        Discord discord = new Discord(testToken);
        String result = discord.fetchLatestLink(oldUrl).getRefreshedUrls().get(0).getRefreshed();

        System.out.println("Result: " + result);

        assert result != null && !result.isEmpty();
    }
}