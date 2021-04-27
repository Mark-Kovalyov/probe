use std::env;
use std::io::prelude::*;
use flate2::Compression;
use flate2::write::ZlibEncoder;

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() == 1 {
        println!("Usage: log-split [--input FOLDER_NAME] [--date-time-format DATE_TIME_FORMAT] [--granularity {{S|H|D}}] OUTPUT_FOLDER_NAME");
    } else {
        println!("Go-go!");
    }
    
}
