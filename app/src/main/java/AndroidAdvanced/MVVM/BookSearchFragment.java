package AndroidAdvanced.MVVM;

import AndroidAdvanced.MVVM.Adapter.BookSearchResultsAdapter;
import AndroidAdvanced.MVVM.Data.Volume;
import AndroidAdvanced.MVVM.ViewModel.BookSearchViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aio_android.R;
import com.google.android.material.textfield.TextInputEditText;
import io.reactivex.rxjava3.annotations.NonNull;

import java.util.List;

public class BookSearchFragment extends Fragment {
    private BookSearchViewModel viewModel;
    private BookSearchResultsAdapter adapter;

    private TextInputEditText keywordEditText, authorEditText;
    private Button searchButton, inputButton;

    private List<Volume> volumeList = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookSearchResultsAdapter();

        viewModel = ViewModelProviders.of(this).get(BookSearchViewModel.class);
        viewModel.getVolumesResponseLiveData().observe(this, new Observer<List<Volume>>() {
            @Override
            public void onChanged(List<Volume> volumeList) {
                if (volumeList != null) {

                    // Adapter부분 세팅 - room에서 가져온걸로 바꿔줄 생각임.
                    adapter.setResults(volumeList);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mvvm_fragment_booksearch, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_booksearch_keyword);
        authorEditText = view.findViewById(R.id.fragment_booksearch_author);
        searchButton = view.findViewById(R.id.fragment_booksearch_search);

        searchButton.setOnClickListener(v -> performSearch());

        //button
        //result.toArray(new Picsum_Model[0]
        inputButton = view.findViewById(R.id.adding_data_to_room);
        inputButton.setOnClickListener(v -> loadAndInsertButton());

        return view;
    }

    public void performSearch() {
        String keyword = keywordEditText.getEditableText().toString();
        String author = authorEditText.getEditableText().toString();
        viewModel.searchVolumes(keyword, author, 1);
    }

    public void loadAndInsertButton()  {
        String keyword = keywordEditText.getEditableText().toString();
        String author = authorEditText.getEditableText().toString();
        viewModel.searchVolumes(keyword, author, 3);

//        viewModel.loadAndInsert(keyword, author);
    }
}
