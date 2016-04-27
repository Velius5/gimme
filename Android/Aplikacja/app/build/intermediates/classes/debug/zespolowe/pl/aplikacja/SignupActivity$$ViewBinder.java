// Generated code from Butter Knife. Do not modify!
package zespolowe.pl.aplikacja;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SignupActivity$$ViewBinder<T extends zespolowe.pl.aplikacja.SignupActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492977, "field '_emailText'");
    target._emailText = finder.castView(view, 2131492977, "field '_emailText'");
    view = finder.findRequiredView(source, 2131492978, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131492978, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131492980, "field '_signupButton'");
    target._signupButton = finder.castView(view, 2131492980, "field '_signupButton'");
    view = finder.findRequiredView(source, 2131492981, "field '_loginLink'");
    target._loginLink = finder.castView(view, 2131492981, "field '_loginLink'");
    view = finder.findRequiredView(source, 2131492979, "field '_passwordText2'");
    target._passwordText2 = finder.castView(view, 2131492979, "field '_passwordText2'");
  }

  @Override public void unbind(T target) {
    target._emailText = null;
    target._passwordText = null;
    target._signupButton = null;
    target._loginLink = null;
    target._passwordText2 = null;
  }
}
