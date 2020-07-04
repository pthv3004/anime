package crawler.helper;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
public class ParsingResult<T> {
    private Collection<T> data;
    private String pageNext;
}