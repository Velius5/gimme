// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Splac_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Splac_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493033, "field '_zaplac'");
    target._zaplac = finder.castView(view, 2131493033, "field '_zaplac'");
  }

  @Override public void unbind(T target) {
    target._zaplac = null;
  }
}
