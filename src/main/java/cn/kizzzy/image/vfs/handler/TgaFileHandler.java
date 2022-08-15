package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.image.TgaFile;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.io.SeekType;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.stream.SliceInputStreamGetter;

import java.io.IOException;

public class TgaFileHandler implements IFileHandler<TgaFile> {
    
    @Override
    public TgaFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        reader.setLittleEndian(true);
        
        TgaFile tgaFile = new TgaFile();
        tgaFile.header = readHeader(reader);
        tgaFile.iocmd = readImageOrColorMapData(reader, tgaFile);
        if (size - reader.position() == 26) {
            tgaFile.footer = readFooter(reader);
        }
        return tgaFile;
    }
    
    private TgaFile.Header readHeader(IFullyReader reader) throws IOException {
        TgaFile.Header header = new TgaFile.Header();
        header.idLength = reader.readUnsignedByte();
        header.colorMapType = reader.readByte();
        header.imageType = reader.readByte();
        header.colorMapSpecification = readColorMapSpecification(reader);
        header.imageSpecification = readImageSpecification(reader);
        return header;
    }
    
    private TgaFile.ColorMapSpecification readColorMapSpecification(IFullyReader reader) throws IOException {
        TgaFile.ColorMapSpecification colorMapSpecification = new TgaFile.ColorMapSpecification();
        colorMapSpecification.firstIndexEntry = reader.readUnsignedShort();
        colorMapSpecification.colorMapLength = reader.readUnsignedShort();
        colorMapSpecification.colorMapEntrySize = reader.readUnsignedByte();
        return colorMapSpecification;
    }
    
    private TgaFile.ImageSpecification readImageSpecification(IFullyReader reader) throws IOException {
        TgaFile.ImageSpecification imageSpecification = new TgaFile.ImageSpecification();
        imageSpecification.xOrigin = reader.readShort();
        imageSpecification.yOrigin = reader.readShort();
        imageSpecification.width = reader.readShort();
        imageSpecification.height = reader.readShort();
        imageSpecification.pixelDepth = reader.readByte();
        imageSpecification.imageDescriptor = reader.readByte();
        return imageSpecification;
    }
    
    private TgaFile.ImageOrColorMapData readImageOrColorMapData(IFullyReader reader, TgaFile tgaFile) throws IOException {
        TgaFile.ImageOrColorMapData iocmd = new TgaFile.ImageOrColorMapData();
        if (tgaFile.header.idLength > 0) {
            iocmd.imageIds = reader.readBytes(tgaFile.header.idLength);
            // todo
        }
        if (tgaFile.header.colorMapType != 0) {
            iocmd.colorMapData = new SliceInputStreamGetter(tgaFile, 0, 0);
            // todo
        }
        if (tgaFile.header.imageType != 0) {
            TgaFile.ImageSpecification is = tgaFile.header.imageSpecification;
            int size = is.width * is.height * is.pixelDepth / 8;
            iocmd.imageData = new SliceInputStreamGetter(tgaFile, reader.position(), size);
            reader.seek(size, SeekType.CURRENT);
        }
        return iocmd;
    }
    
    private TgaFile.Footer readFooter(IFullyReader reader) throws IOException {
        TgaFile.Footer footer = new TgaFile.Footer();
        footer.extensionAreaOffset = reader.readUnsignedInt();
        footer.developerDirectoryOffset = reader.readUnsignedInt();
        footer.signature = reader.readString(16);
        footer.endCharacter = reader.readByte();
        footer.eof = reader.readByte();
        return footer;
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, TgaFile data) throws Exception {
        writer.setLittleEndian(true);
        
        // todo
        return false;
    }
}
