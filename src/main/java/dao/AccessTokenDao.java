package dao;

import com.google.common.base.Optional;
import domain.AccessToken;
import domain.Person;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AccessTokenDao {

    private static Map<UUID, AccessToken> accessTokenTable = new HashMap<UUID, AccessToken>();

    public Optional<AccessToken> findAccessTokenById(final UUID accessTokenId) {
        AccessToken accessToken = accessTokenTable.get(accessTokenId);
        if (accessToken == null) {
            return Optional.absent();
        }
        return Optional.of(accessToken);
    }

    public AccessToken generateNewAccessToken(final Person person, final DateTime dateTime) {
        AccessToken accessToken = new AccessToken(UUID.randomUUID(), person, dateTime);
        accessTokenTable.put(accessToken.getAccessTokenId(), accessToken);
        return accessToken;
    }

    public void setLastAccessTime(final UUID accessTokenUUID, final DateTime dateTime) {
        AccessToken accessToken = accessTokenTable.get(accessTokenUUID);
        AccessToken updatedAccessToken = accessToken.withLastAccessUTC(dateTime);
        accessTokenTable.put(accessTokenUUID, updatedAccessToken);
    }
}
