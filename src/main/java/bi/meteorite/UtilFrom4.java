package bi.meteorite;

/**
 * Util class.
 *
 * Created by martin on 2015-12-01
 */
public class UtilFrom4 {

    /**
     * Function that takes one argument ({@code PT}) and returns {@code RT}.
     *
     * @param <RT> Return type
     * @param <PT> Parameter type
     */
    public static interface Function1<PT, RT> {
        RT apply(PT param);
    }
}
