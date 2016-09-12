package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import lombok.experimental.Wither;

@Wither
public class AccessToken {

    public AccessToken() {
    }

    public AccessToken(UUID accessTokenId, Person person, DateTime lastAccessUTC) {
        this.accessTokenId = accessTokenId;
        this.person = person;
        this.lastAccessUTC = lastAccessUTC;
    }

    @JsonProperty("access_token_id")
    @NotNull
    private UUID accessTokenId;

    @JsonProperty("user_id")
    @NotNull
    private Person person;

    @JsonProperty("last_access_utc")
    @NotNull
    private DateTime lastAccessUTC;

    public void setAccessTokenId(UUID accessTokenId) {
        this.accessTokenId = accessTokenId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DateTime getLastAccessUTC() {
        return lastAccessUTC;
    }

    public void setLastAccessUTC(DateTime lastAccessUTC) {
        this.lastAccessUTC = lastAccessUTC;
    }

    public UUID getAccessTokenId() {
        return accessTokenId;
    }
}
