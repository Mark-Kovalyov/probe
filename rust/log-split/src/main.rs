use std::env;
use std::io::prelude::*;

use flate2::Compression;
use flate2::write::ZlibEncoder;

use std::collections::hash_map::DefaultHasher;
use std::hash::{Hash, Hasher};

use std::fs;

fn enumerateFiles() {
    let paths = fs::read_dir("./").unwrap();
    for path in paths {
        println!("Name: {}", path.unwrap().path().display())
    }
}

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() == 1 {
        println!("Usage: log-split [--input FOLDER_NAME] [--date-time-format DATE_TIME_FORMAT] [--granularity {{S|H|D}}] OUTPUT_FOLDER_NAME");
    } else {
        println!("Go-go!");
    }
    
}
