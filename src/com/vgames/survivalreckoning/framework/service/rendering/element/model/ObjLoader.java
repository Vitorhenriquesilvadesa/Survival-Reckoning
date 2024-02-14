package com.vgames.survivalreckoning.framework.service.rendering.element.model;

import com.vgames.survivalreckoning.framework.log.annotation.GenerateCriticalFile;
import com.vgames.survivalreckoning.framework.math.Vector2;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.rendering.AssetLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@GenerateCriticalFile
public class ObjLoader extends AssetLoader {

    public RawModel loadModel(String modelPath, RawModelLoader loader) {
        FileReader fr = null;

        try {
            fr = new FileReader(modelPath + ".obj");
        } catch (FileNotFoundException e) {
            critical("OBJ File not found.", new RuntimeException(e));
        }

        if (fr != null) {
            BufferedReader reader = new BufferedReader(fr);
            String line;
            List<Vector3> vertices = new ArrayList<>();
            List<Vector2> textureCoords = new ArrayList<>();
            List<Vector3> normals = new ArrayList<>();
            List<Integer> indices = new ArrayList<>();

            float[] verticesArray;
            float[] normalsArray;
            float[] textureCoordsArray;
            int[] indicesArray;

            try {
                while (true) {
                    line = reader.readLine();
                    String[] currentLine = line.split(" ");
                    if (line.startsWith("v ")) {
                        Vector3 vertex = new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                        vertices.add(vertex);
                    } else if (line.startsWith("vt ")) {
                        Vector2 texture = new Vector2(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                        textureCoords.add(texture);
                    } else if (line.startsWith("vn ")) {
                        Vector3 normal = new Vector3(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                        normals.add(normal);
                    } else if (line.startsWith("f ")) {
                        textureCoordsArray = new float[vertices.size() * 2];
                        normalsArray = new float[vertices.size() * 3];
                        break;
                    }
                }

                while (line != null) {
                    if (!line.startsWith("f ")) {
                        line = reader.readLine();
                        continue;
                    }
                    String[] currentLine = line.split(" ");
                    String[] vertex1 = currentLine[1].split("/");
                    String[] vertex2 = currentLine[2].split("/");
                    String[] vertex3 = currentLine[3].split("/");

                    processVertex(vertex1, indices, textureCoords, normals, textureCoordsArray, normalsArray);
                    processVertex(vertex2, indices, textureCoords, normals, textureCoordsArray, normalsArray);
                    processVertex(vertex3, indices, textureCoords, normals, textureCoordsArray, normalsArray);

                    line = reader.readLine();
                }

                reader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            verticesArray = new float[vertices.size() * 3];
            indicesArray = new int[indices.size()];

            int vertexPointer = 0;
            for(Vector3 vertex : vertices) {
                verticesArray[vertexPointer++] = vertex.x;
                verticesArray[vertexPointer++] = vertex.y;
                verticesArray[vertexPointer++] = vertex.z;
            }

            for(int i = 0; i < indices.size(); i++) {
                indicesArray[i] = indices.get(i);
            }

            return loader.loadToVAO(verticesArray, textureCoordsArray, normalsArray, indicesArray);
        }

        throw new IllegalStateException("Could not reader provided obj file: " + modelPath);
    }

    private void processVertex(String[] vertexData, List<Integer> indices, List<Vector2> textureCoords, List<Vector3> normals, float[] textureArray, float[] normalsArray) {

        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector2 currentTexture = textureCoords.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPointer * 2] = currentTexture.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTexture.y;

        Vector3 currentNormal = normals.get(Integer.parseInt(vertexData[2]) - 1);

        normalsArray[currentVertexPointer * 3] = currentNormal.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNormal.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNormal.z;
    }
}
