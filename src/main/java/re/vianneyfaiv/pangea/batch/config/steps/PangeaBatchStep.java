package re.vianneyfaiv.pangea.batch.config.steps;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.PassThroughItemProcessor;

public interface PangeaBatchStep<V> {

	public ItemWriter<V> writer();

	public FlatFileItemReader<V> reader();

	default public PassThroughItemProcessor<V> processor() {
		return new PassThroughItemProcessor<V>();
	};
}
