package com.guyuan.dear.databinding;
import com.guyuan.dear.R;
import com.guyuan.dear.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivityExamBindingImpl extends ActivityExamBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(11);
        sIncludes.setIncludes(0, 
            new String[] {"layout_home_bar"},
            new int[] {1},
            new int[] {com.guyuan.dear.R.layout.layout_home_bar});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_question, 2);
        sViewsWithIds.put(R.id.rg_select, 3);
        sViewsWithIds.put(R.id.rb_a, 4);
        sViewsWithIds.put(R.id.rb_b, 5);
        sViewsWithIds.put(R.id.rb_c, 6);
        sViewsWithIds.put(R.id.rb_d, 7);
        sViewsWithIds.put(R.id.tv_parse, 8);
        sViewsWithIds.put(R.id.tv_previous, 9);
        sViewsWithIds.put(R.id.tv_next, 10);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityExamBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ActivityExamBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.guyuan.dear.databinding.LayoutHomeBarBinding) bindings[1]
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[4]
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[5]
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[6]
            , (androidx.appcompat.widget.AppCompatRadioButton) bindings[7]
            , (android.widget.RadioGroup) bindings[3]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[2]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        llHomeBar.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (llHomeBar.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        llHomeBar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLlHomeBar((com.guyuan.dear.databinding.LayoutHomeBarBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLlHomeBar(com.guyuan.dear.databinding.LayoutHomeBarBinding LlHomeBar, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(llHomeBar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): llHomeBar
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}