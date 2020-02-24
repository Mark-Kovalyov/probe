use std::fs::File;
use std::thread;


fn go() {
    let child = thread::spawn(move || {
         // some work here

    });
    // some work here
    let res = child.join();
}

fn main() {

    // The decoder is a build for reader and can be used to set various decoding options
    // via `Transformations`. The default output transformation is `Transformations::EXPAND
    // | Transformations::STRIP_ALPHA`.

    let file1e = File::open("/home/mayton/git/probe/islands/islands-rust/image-02-2048x2048.png");
    let log1   = File::create("logs/application.log");
    //log.write_all(b"Begin...");

    //let decoder = png::Decoder::new(file1.unwrap());

    //let (info, mut reader) = decoder.read_info().unwrap();
    // Allocate the output buffer.

    //let mut buf = vec![0; info.buffer_size()];
    // Read the next frame. Currently this function should only called once.
    // The default options

    //reader.next_frame(&mut buf).unwrap();


    //log.sync_all();
    print!("OK!\n");
}
