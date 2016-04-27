package zespolowe.pl.aplikacja.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import zespolowe.pl.aplikacja.model.Friend;
import zespolowe.pl.aplikacja.model.Product;
import zespolowe.pl.aplikacja.model.User;

public final class MultiSpinner extends Spinner implements OnMultiChoiceClickListener, OnCancelListener {

    private List<Friend>       items;
    private boolean[]            selected;
    private String               allText;
    private MultiSpinnerListener listener;
    private Product product;

    public MultiSpinner( Context context ) {
        super( context );
    }

    public MultiSpinner( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    public MultiSpinner( Context context, AttributeSet attrs, int defStyle ) {
        super( context, attrs, defStyle );
    }

    @Override
    public void onClick( DialogInterface dialog, int which, boolean isChecked ) {
        selected[ which ] = isChecked;
    }

    @Override
    public void onCancel( DialogInterface dialog ) {

        // refresh text on spinner.
        final StringBuffer b = new StringBuffer();
        boolean anyUnselected = false;
        for( int i = 0; i < items.size(); i++ )
            if( selected[ i ] ) {
                b.append( items.get( i ).getName());
                b.append( ", " );
            } else {
                anyUnselected = true;
            }

        String text;
        if( anyUnselected ) {
            text = b.toString();
            if( text.length() > 2 )
                text = text.substring( 0, text.length() - 2 );
        } else {
            text = allText;
        }

        ArrayAdapter< String > adapter = new ArrayAdapter< String >( //
                getContext(), //
                android.R.layout.simple_spinner_item, //
                new String[] { text } //
        );
        setAdapter(adapter);

        if( listener != null )
            listener.onItemsSelected( selected );
    }

    @Override
    public boolean performClick() {
        List<String> friendsNameAndSurname = new ArrayList<>();
        if( items == null || items.size() < 1 )
            return false;

        AlertDialog.Builder builder = new AlertDialog.Builder( getContext() );
        Integer i = 0;
        for(Friend f : items) {
            friendsNameAndSurname.add(f.getName() + " " + f.getSurname());
            if(product.getUsers() != null) {
                for (User user : product.getUsers()) {
                    if (f.getId() == user.getId()) {
                        selected[i] = true;
                    } else {
                        selected[i] = false;
                    }
                }
                i++;
            }
        }

        builder.setMultiChoiceItems( friendsNameAndSurname.toArray( new CharSequence[ friendsNameAndSurname.size() ] ), selected, this );
        builder.setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.cancel();
            }
        } );
        builder.setOnCancelListener(this);
        builder.show();
        return true;
    }

    public void setItems( List< Friend > items, String allText, Product product ) {
        setItems(items, allText, null, product);
        System.out.println(product.toString());
    }

    public void setItems( List< Friend > items, String allText, MultiSpinnerListener listener, Product product ) {
        this.items = items;
        this.allText = allText;
        this.listener = listener;
        this.product = product;

        // all selected by default.
        selected = new boolean[ 100 ];
//        for( int i = 0; i < items.size(); i++ )
//            selected[ i ] = false;

        // all text on the spinner.
        ArrayAdapter< String > adapter = new ArrayAdapter< String >( //
                getContext(), //
                android.R.layout.simple_spinner_item, //
                new String[] { allText } //
        );
        setAdapter(adapter);
    }

    /**
     * Return a list with all selected items OR:
     * null - if ALL items were selected;
     * empty list - if no items were selected;
     * normal list - normal selection.
     */
    public List< Friend > getSelectedItems() {

        final int sl = selected.length;
        final List< Friend > list = new ArrayList< Friend >( sl );

        for( int i = 0; i < sl; i++ )
            if( selected[ i ] )
                list.add( items.get( i ) );

        if( list.size() == sl )
            return null;

        return list;
    }


    public interface MultiSpinnerListener {
        public void onItemsSelected( boolean[] selected );
    }

    public Product getProduct() {
        return product;
    }
}