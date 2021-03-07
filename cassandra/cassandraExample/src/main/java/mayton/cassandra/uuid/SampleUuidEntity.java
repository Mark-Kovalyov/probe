package mayton.cassandra.uuid;

import org.restexpress.plugin.hyperexpress.Linkable;
import mayton.cassandra.Constants;
import mayton.cassandra.serialization.UuidFormatter;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;
import com.strategicgains.repoexpress.domain.AbstractUuidEntity;

@TokenBindings({
	@BindToken(value=Constants.Url.UUID, field="id", formatter=UuidFormatter.class)
})
public class SampleUuidEntity
extends AbstractUuidEntity
implements Linkable
{
	public SampleUuidEntity()
	{
	}
}
