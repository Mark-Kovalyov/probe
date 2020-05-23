std::vector<int> count_lines_in_files(const std::vector<std::string>& files) {
  std::vector<int> results;
  char c = 0;
  for(const auto& file : files) {
    int line_count = 0;
    std::ifstream in(file);
    while(in.get(c))
      if (c == '\n')
        line_count++;
    results.push_back(line_count);
    return results;
}


