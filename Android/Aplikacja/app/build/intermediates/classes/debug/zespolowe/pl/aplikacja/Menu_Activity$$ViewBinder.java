// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Menu_Activity$$ViewBinder<T extends zespolowe.pl.aplikacja.Menu_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493001, "field 'camera'");
    target.camera = finder.castView(view, 2131493001, "field 'camera'");
    view = finder.findRequiredView(source, 2131493002, "field 'gallery'");
    target.gallery = finder.castView(view, 2131493002, "field 'gallery'");
    view = finder.findRequiredView(source, 2131493003, "field 'znajomi'");
    target.znajomi = finder.castView(view, 2131493003, "field 'znajomi'");
    view = finder.findRequiredView(source, 2131493006, "field 'getapi'");
    target.getapi = finder.castView(view, 2131493006, "field 'getapi'");
  }

  @Override public void unbind(T target) {
    target.camera = null;
    target.gallery = null;
    target.znajomi = null;
    target.getapi = null;
  }
}
