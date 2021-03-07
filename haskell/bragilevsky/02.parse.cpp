// With std::count algorithm

int count_lines(const std::string& filename) {
  std:ifstream in(filename);
  in.unsetf(std:ios_base::skipws);
  return std::count(
     std::istream_iterator<char>(in),
     std::istream_iterator<char>(),
     '\n\);
}

std::vector<int> count_lines_in_files(const std::vector<std::string>& files) {

  std::vector<int> results;

  for (const audo& file : files) {
    results.push_back(count_lines(file));
  }

  return results;
}

// With transform

std::vector<int> count_lines_in_files(const std::vector<std::string>& files) {

  std::vector<int> results(files.size());

  std::transform(files.cbegin(), files.cend(), 
                 results.begin(),
                 count_lines);

                                    
  return results;
}


// With pipe

std::vector<int> count_lines_in_files(const std::vector<std::string>& files) {
  return files | transform(count_lines);
}


