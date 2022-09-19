package AndroidAdvanced.MVVM.Adapter;

import AndroidAdvanced.MVVM.Data.Volume;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.aio_android.databinding.MvvmBookItemBinding;
import io.reactivex.rxjava3.annotations.NonNull;
import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookSearchResultHolder> {

    private List<Volume> results = new ArrayList<>();
    private MvvmBookItemBinding binding;

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MvvmBookItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookSearchResultHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        holder.bindItem(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder {

        public BookSearchResultHolder(@NonNull MvvmBookItemBinding itemBinding) {
            super(itemBinding.getRoot());
        }

        void bindItem(Volume item){
            binding.bookItemTitle.setText(item.getVolumeInfo().getTitle());
            binding.bookItemPublishedDate.setText(item.getVolumeInfo().getPublishedDate());

            if (item.getVolumeInfo().getImageLinks() != null) {
                String imageUrl = item.getVolumeInfo().getImageLinks().getSmallThumbnail()
                        .replace("http://", "https://");

                Glide.with(itemView)
                        .load(imageUrl)
                        .into(binding.bookItemSmallThumbnail);
            }

            if (item.getVolumeInfo().getAuthors() != null) {
                StringBuilder sb = new StringBuilder();
                for(String author : item.getVolumeInfo().getAuthors()){
                    sb.append(author).append(", ");
                }
                String authors = "";
                if(sb.length() > 0){
                        authors = sb.substring(0, sb.length()-1);
                }
                binding.bookItemAuthors.setText(authors);
            }
        }
    }
}
