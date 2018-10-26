import java.util.Scanner;

public class Q7 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            convolution();
        }
    }

    private static void convolution() {
        Kernel kernel = new Kernel();
        Image image = new Image(kernel);
        image.apply();
    }

    static class Kernel {
        int size;
        double[][] filter;

        Kernel() {
            size = scanner.nextInt();
            filter = new double[size][size];
            for (int i = size - 1; i >= 0; i--) {
                for (int j = size - 1; j >= 0; j--) {
                    filter[i][j] = scanner.nextDouble();
                }
            }
        }
    }

    static class Image {
        Kernel kernel;

        int width;
        int height;
        int[][] pixels;
        int offset;
        int offsetH;
        int offsetW;
        int fullH;
        int fullW;

        Image(Kernel kernel) {
            this.kernel = kernel;
            height = scanner.nextInt();
            width = scanner.nextInt();

            offset = kernel.size / 2;
            offsetH = offset + height;
            offsetW = offset + width;
            fullH = offset * 2 + height;
            fullW = offset * 2 + width;

            pixels = new int[fullH][fullW];
            for (int i = offset; i < offsetH; i++) {
                for (int j = offset; j < offsetW; j++) {
                    pixels[i][j] = scanner.nextInt();
                }
            }
            // upper left
            for (int i = 0; i < offset; i++) {
                for (int j = 0; j < offset; j++) {
                    pixels[i][j] = pixels[offset][offset];
                }
            }
            // upper right
            for (int i = 0; i < offset; i++) {
                for (int j = offsetW; j < fullW; j++) {
                    pixels[i][j] = pixels[offset][offsetW - 1];
                }
            }
            // lower left
            for (int i = offsetH; i < fullH; i++) {
                for (int j = 0; j < offset; j++) {
                    pixels[i][j] = pixels[offsetH - 1][offset];
                }
            }
            // lower right
            for (int i = offsetH; i < fullH; i++) {
                for (int j = offsetW; j < fullW; j++) {
                    pixels[i][j] = pixels[offsetH - 1][offsetW - 1];
                }
            }
            // left
            for (int i = offset; i < offsetH; i++) {
                for (int j = 0; j < offset; j++) {
                    pixels[i][j] = pixels[i][offset];
                }
            }
            // upper
            for (int i = 0; i < offset; i++) {
                for (int j = offset; j < offsetW; j++) {
                    pixels[i][j] = pixels[offset][j];
                }
            }
            // right
            for (int i = offset; i < offsetH; i++) {
                for (int j = offsetW; j < fullW; j++) {
                    pixels[i][j] = pixels[i][offsetW - 1];
                }
            }
            // lower
            for (int i = offsetH; i < fullH; i++) {
                for (int j = offset; j < offsetW; j++) {
                    pixels[i][j] = pixels[offsetH - 1][j];
                }
            }
        }

        public void apply() {
            for (int i = offset; i < offsetH; i++) {
                for (int j = offset; j < offsetW; j++) {
                    double sum = 0;
                    for (int y = 0; y < kernel.size; y++) {
                        for (int x = 0; x < kernel.size; x++) {
                            int ry = i - offset + y;
                            int rx = j - offset + x;
                            sum += pixels[ry][rx] * kernel.filter[y][x];
                        }
                    }
                    int answer = Math.min(Math.max((int) Math.round(sum), 0), 255);
                    System.out.printf("%3d ", answer);
                }
                System.out.println();
            }
        }
    }

}
