// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Camera_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Camera_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492986, "field '_imageprev'");
    target._imageprev = finder.castView(view, 2131492986, "field '_imageprev'");
  }

  @Override public void unbind(T target) {
    target._imageprev = null;
  }
}
